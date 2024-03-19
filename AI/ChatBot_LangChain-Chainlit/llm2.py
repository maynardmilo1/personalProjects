import chainlit as cl
from transformers import AutoTokenizer, AutoModelForSeq2SeqLM
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.chains import ConversationalRetrievalChain
from langchain.docstore.document import Document
from langchain.memory import ChatMessageHistory, ConversationBufferMemory
# model_name = "tiiuae/falcon-7b-instruct"
# meta-llama/Llama-2-7b-chat-hf
# google/gemma-7b
def process_text_file(file):

    text = file.read().decode("utf-8")
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=40)
    return text_splitter.split_text(text)

def create_conversational_chain(texts, model_name="google/gemma-7b"):
    """Creates a ConversationalRetrievalChain using the latest pipeline.

    Args:
        texts: A list of text chunks from the processed file.
        model_name (str, optional): The name of the Hugging Face model to use. Defaults to "google/gemma-7b-it".

    Returns:
        ConversationalRetrievalChain: The created chain for question answering.
    """

    # Use the latest pipeline for text-davinci-003 model (or your preferred choice)
    pipeline = AutoModelForSeq2SeqLM.from_pretrained(model_name)
    tokenizer = AutoTokenizer.from_pretrained(model_name, max_length = 4096)

    def query(message):
        """Queries the model using the pipeline."""
        inputs = tokenizer(message, return_tensors="pt")
        outputs = pipeline.generate(**inputs, max_length = 4096)
        return tokenizer.decode(outputs[0], skip_special_tokens=True)

    message_history = ChatMessageHistory()
    memory = ConversationBufferMemory(
        memory_key="chat_history", output_key="answer", chat_memory=message_history
    )

    return ConversationalRetrievalChain.from_query_function(
        query_function=query, memory=memory
    )

@cl.on_chat_start
async def on_chat_start():
    """Handles initial message and file upload."""

    files = None

    while files is None:
        files = await cl.AskFileMessage(
            content="Please upload a text file to begin!",
            accept=["text/plain"],
            max_size_mb=20,
            timeout=180,
        ).send()

    file = files[0]

    msg = cl.Message(content=f"Processing `{file.name}`...")
    await msg.send()

    text_chunks = process_text_file(file)

    chain = create_conversational_chain(text_chunks)

    msg.content = f"Processing complete. You can now ask questions!"
    await msg.update()

    cl.user_session.set("chain", chain)

@cl.on_message
async def main(message: cl.Message):
    """Handles user messages and provides answers."""

    chain = cl.user_session.get("chain")  # type: ConversationalRetrievalChain
    res = await chain.acall(message, callbacks=[cl.AsyncLangchainCallbackHandler()])

    answer = res["answer"]
    await cl.Message(content=answer).send()

if __name__ == "__main__":
    cl.app(main)
