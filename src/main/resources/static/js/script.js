// Сохраните этот код в src/main/resources/static/js/script.js

// Функция для тестирования загрузки файла
function testScriptLoad() {
  console.log('script.js успешно загружен и работает!');
}

function performSearch() {
  const searchQuery = document.getElementById('searchInput').value;
  // Отправка запроса на сервер и обработка ответа
  // Необходимо добавить URL для поиска
}

function loadPopularBrands() {
  // Запрос к серверу за популярными брендами
  // Необходимо добавить URL для получения данных
}

function loadPopularModels() {
  // Запрос к серверу за популярными моделями
  // Необходимо добавить URL для получения данных
}

// Инициализация тестовой функции и функций загрузки при открытии страницы
document.addEventListener('DOMContentLoaded', function() {
  testScriptLoad();
  loadPopularBrands();
  loadPopularModels();
});

