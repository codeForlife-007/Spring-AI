import React, { useState } from "react";

function Chat() {
    const [prompt, setPrompt] = useState('');
    const [chatResponse, setChatResponse] = useState('');
    const [loading, setLoading] = useState(false);

    const askAI = async () => {
        setLoading(true);
        try {
            const response = await fetch(`http://localhost:8080/ask-ai?prompt=${prompt}`);
            const data = await response.text();
            setChatResponse(data);
        } catch (error) {
            console.error("Error generating response", error);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <h2>
                Talk to AI
            </h2>
            <input 
                type="text"
                name="prompt"
                value={prompt}
                onChange={(e) => setPrompt(e.target.value)}
                placeholder="Enter a prompt for AI"
                />

            <button onClick={askAI}>Ask AI</button>
            <div className="output">
                {loading && <h3 className="centered-text">loading...</h3>}
                <p>{chatResponse}</p>
            </div>
        </div>
    );
}

export default Chat;