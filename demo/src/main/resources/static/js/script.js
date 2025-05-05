//  Smooth transition for the entire page load
window.addEventListener("load", () => {
    document.body.classList.add("loaded");
});

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
