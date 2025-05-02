function showToast() {
    var toast = document.getElementById("toast");
    toast.classList.add("show");
  
    setTimeout(function () {
      toast.classList.remove("show");
    }, 3000); // Toast disappears after 3 seconds
  }
  