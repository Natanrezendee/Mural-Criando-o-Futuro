document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('form-contato');
    form.addEventListener('submit', enviarFormulario);
});
function enviarFormulario(event) {
    event.preventDefault();

    const form = document.getElementById('form-contato');
    //const formData = new FormData(form);

    fetch('/api/contato', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => response.text())
        .then(message => {
            if (message.includes('sucesso')) {
                Swal.fire({
                    title: 'Sucesso!',
                    text: 'Mensagem encaminhada com sucesso!',
                    icon: 'success',
                    confirmButtonText: 'Fechar',
                });
                form.reset();
            } else {
                Swal.fire({
                    title: 'Erro!',
                    text: 'Erro ao enviar mensagem. Tente novamente mais tarde.',
                    icon: 'error',
                    confirmButtonText: 'Fechar',
                });
            }
        })
        .catch(error => {
            console.error('Erro:', error);
            Swal.fire({
                title: 'Erro!',
                text: 'Erro ao enviar mensagem. Tente novamente mais tarde.',
                icon: 'error',
                confirmButtonText: 'Fechar',
            });
        });
}