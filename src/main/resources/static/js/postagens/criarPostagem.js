document.addEventListener("DOMContentLoaded", function () {
    const easyMDE = new EasyMDE({
        element: document.getElementById("postagem"),
        spellChecker: false,
        placeholder: "Escreva sua notícia em Markdown...",
        previewImagesInEditor: true,
        renderingConfig: {
            codeSyntaxHighlighting: true,
        },
    });
});

document.querySelector('form').addEventListener('submit', function (event) {
    const easyMDEContent = easyMDE.value().trim();
    if (!easyMDEContent) {
        alert("O conteúdo da notícia não pode estar vazio.");
        event.preventDefault();
    }
});

// Seleciona a área de drag-drop e o input de arquivo
const dragDropArea = document.getElementById('drag-drop-area');
const fileInput = document.getElementById('imagens');
const previewContainer = document.querySelector('.preview-container');

// Função para mostrar as imagens selecionadas
function showPreview(files) {
    Array.from(files).forEach(file => {
        const reader = new FileReader();
        reader.onload = function (e) {
            const imageContainer = document.createElement('div');
            imageContainer.classList.add('image-container');

            const img = document.createElement('img');
            img.src = e.target.result;
            img.classList.add('preview-image');

            const removeButton = document.createElement('button');
            removeButton.classList.add('remove-btn');
            removeButton.textContent = '×';

            removeButton.addEventListener('click', function () {
                previewContainer.removeChild(imageContainer);
            });

            imageContainer.appendChild(img);
            imageContainer.appendChild(removeButton);

            previewContainer.appendChild(imageContainer);
        };
        reader.readAsDataURL(file);
    });
}

dragDropArea.addEventListener('dragover', function (e) {
    e.preventDefault();
    dragDropArea.classList.add('drag-over');
});

dragDropArea.addEventListener('dragleave', function () {
    dragDropArea.classList.remove('drag-over');
});

dragDropArea.addEventListener('drop', function (e) {
    e.preventDefault();
    dragDropArea.classList.remove('drag-over');
    const files = e.dataTransfer.files;
    if (files.length > 0) {
        showPreview(files);
    }
});

dragDropArea.addEventListener('click', function () {
    fileInput.click();
});

fileInput.addEventListener('change', function () {
    showPreview(fileInput.files);
});