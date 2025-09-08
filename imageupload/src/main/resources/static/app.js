const API_URL = "http://localhost:8080/api/images";

document.getElementById("uploadForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const fileInput = document.getElementById("fileInput");
    const formData = new FormData();
    formData.append("file", fileInput.files[0]);

    await fetch(API_URL + "/upload", {
        method: "POST",
        body: formData
    });

    loadImages();
});

async function loadImages() {
    const res = await fetch(API_URL);
    const images = await res.json();
    const gallery = document.getElementById("gallery");
    gallery.innerHTML = "";

    images.forEach(img => {
        const imageElement = document.createElement("img");
        imageElement.src = img.url;
        gallery.appendChild(imageElement);
    });
}

loadImages();