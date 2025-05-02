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
