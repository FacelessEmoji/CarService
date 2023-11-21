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


document.addEventListener('DOMContentLoaded', function() {
  // Получаем все радиокнопки для выбора сущности поиска
  var searchEntityRadios = document.querySelectorAll('input[name="searchEntity"]');

  // Функция для обновления видимости критериев поиска
  function updateSearchCriteria() {
    // Скрываем все критерии
    document.querySelectorAll('.criteria').forEach(function(criteria) {
      criteria.style.display = 'none';
    });

    // Показываем критерии для выбранной сущности
    var selectedEntity = document.querySelector('input[name="searchEntity"]:checked').value;
    document.getElementById('criteria-' + selectedEntity).style.display = 'block';
  }

  // Назначаем обработчик события на изменение каждой радиокнопки
  searchEntityRadios.forEach(function(radio) {
    radio.addEventListener('change', updateSearchCriteria);
  });

  // Инициализируем критерии поиска при загрузке страницы
  updateSearchCriteria();
});

// Инициализация тестовой функции и функций загрузки при открытии страницы
document.addEventListener('DOMContentLoaded', function() {
  testScriptLoad();
  loadPopularBrands();
  loadPopularModels();
});

