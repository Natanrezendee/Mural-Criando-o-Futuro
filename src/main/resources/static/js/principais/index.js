document.querySelector('.scroll-down').addEventListener('click', function () {
    document.querySelector('.container.my-5').scrollIntoView({
        behavior: 'smooth'
    });
});