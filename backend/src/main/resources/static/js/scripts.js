// scripts.js

// Add a simple validation message for empty input fields
document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (e) {
        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();

        if (username === "" || password === "") {
            alert("Username and password are required!");
            e.preventDefault(); // Prevent form submission
        }
    });
});
