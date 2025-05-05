export async function askOpenRouterAboutPlace(placeName) {
    try {
      const response = await fetch("https://openrouter.ai/api/v1/chat/completions", {
        method: "POST",
        headers: {
          "Authorization": `Bearer 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`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          model: "deepseek/deepseek-prover-v2:free",
          messages: [
            {
              role: "system",
              content: "You are a helpful assistant that gives short facts about buildings."
            },
            {
              role: "user",
              content: `Tell me something interesting about this place: "${placeName}".`
            }
          ]
        })
      });
  
      const data = await response.json();
      return data.choices?.[0]?.message?.content || "No info found.";
    } catch (err) {
      console.error("OpenRouter error:", err);
      return "AI info unavailable.";
    }
  }
  