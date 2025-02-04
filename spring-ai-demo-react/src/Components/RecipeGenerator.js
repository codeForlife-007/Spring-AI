import React, { useState } from "react";

function RecipeGenerator() {
    const [ingredients, setIngredients] = useState('');
    const [cuisine, setCuisine] = useState('any');
    const [dietaryRestrictions, setDietaryRestrictions] = useState('');
    const [recipe, setRecipe] = useState('');
    const [loading, setLoading] = useState(false);

    const createRecipe = async () => {
        setLoading(true);
        try {
            const response = await fetch(`http://localhost:8080/recipe-creator?ingredients=${ingredients}&dietaryRestrictions=${dietaryRestrictions}&cuisine=${cuisine}`);
            const data = await response.text();
            setRecipe(data);
        } catch (error) {
            console.error("Error generating recipe", error);
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <h2>Create a Recipe</h2>
            <input
                type="text"
                name="ingredients"
                value={ingredients}
                onChange={(e) => setIngredients(e.target.value)}
                placeholder="Enter ingredients (comma seperated)"
                />

            <input
                type="text"
                name="cuisine"
                value={cuisine}
                onChange={(e) => setCuisine(e.target.value)}
                placeholder="Enter cuisine type"
                />

            <input
                type="text"
                name="dietaryRestrictions"
                value={dietaryRestrictions}
                onChange={(e) => setDietaryRestrictions(e.target.value)}
                placeholder="Enter dietary restrictions"
                />

            <button onClick={createRecipe}>Create Recipe</button>

            <div className="output">
                <pre className="recipe-text">
                    {loading && <h3 className="centered-text">loading...</h3>}
                    {recipe}
                </pre>
            </div>
        </div>
    );
}

export default RecipeGenerator;