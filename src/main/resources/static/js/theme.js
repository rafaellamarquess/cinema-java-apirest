// Função para obter o valor de um cookie
function getCookie(name) {
    const cookies = document.cookie.split('; ');
    const cookie = cookies.find(c => c.startsWith(name + '='));
    return cookie ? cookie.split('=')[1] : null;
}

// Função para definir um cookie
function setCookie(name, value, days) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    document.cookie = `${name}=${value}; expires=${date.toUTCString()}; path=/`;
}

// Aplicar tema com base no cookie
function applyTheme(theme) {
    if (theme === 'dark') {
        document.body.classList.add('dark-mode');
        document.body.classList.remove('light-mode');
    } else {
        document.body.classList.add('light-mode');
        document.body.classList.remove('dark-mode');
    }
}

// Alternar tema e salvar no cookie
document.getElementById('theme-toggle').addEventListener('click', () => {
    const currentTheme = document.body.classList.contains('dark-mode') ? 'dark' : 'light';
    const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
    applyTheme(newTheme);
    setCookie('theme', newTheme, 30); // Salvar a preferência por 30 dias
});

// Inicializar tema ao carregar a página
window.addEventListener('DOMContentLoaded', () => {
    const savedTheme = getCookie('theme') || 'light'; // Padrão: tema claro
    applyTheme(savedTheme);
});
