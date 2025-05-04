//  Smooth transition for the entire page load
window.addEventListener("load", () => {
    document.body.classList.add("loaded");
});


// login.js
document.getElementById("loginForm").addEventListener("submit", function(e) {
    e.preventDefault();

    // Clear previous error message
    const errorMessage = document.getElementById("errorMessage");
    errorMessage.textContent = "";

    // Get form values
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    // Simple validation for empty fields
    if (username === "" || password === "") {
        errorMessage.textContent = "Both fields are required!";
    } else {
        // If form is valid, you can send this data to the server
        // Here, we just simulate a successful login for demonstration
        alert("Login successful!");

        // You could redirect to another page after successful login
        window.location.href = "home.html"; // Example redirection
    }
});


// console.log("Script Loaded!");
//     // Function to show the toast message
//     function showToast(message) {
//         const toast = document.getElementById('toast');
//         toast.textContent = message;
//         toast.classList.add('show');

//         // Hide after 3 seconds
//         setTimeout(() => {
//             toast.classList.remove('show');
//         }, 3000);
//     }

//     // Call showToast() after successful update
//     // Example usage: call it if redirected with a flag
//     // For now, show on page load as demo:
//     window.onload = function () {
//         showToast("Contact updated successfully!");
//     };


function showToast(message) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.classList.add('show');
    setTimeout(() => {
        toast.classList.remove('show');
    }, 3000);
}

window.onload = function () {
    const status = new URLSearchParams(window.location.search).get('status');
    if (status === 'added') {
        showToast("Contact added successfully!");
    } else if (status === 'updated') {
        showToast("Contact updated successfully!");
    } else if (status === 'deleted') {
        showToast("Contact deleted successfully!");
    }
};
