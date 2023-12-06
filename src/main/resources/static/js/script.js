document.addEventListener('DOMContentLoaded', function() {
  updateModels(); // Вызов функции при загрузке страницы
});

function updateModels() {
  var brandName = document.getElementById('brand').value;
  fetch('/get-models?brandName=' + brandName)
    .then(response => response.json())
    .then(data => {
      var modelSelect = document.getElementById('model');
      modelSelect.innerHTML = '';

      // Добавляем опцию "All"
      var allOption = document.createElement('option');
      allOption.value = "All";
      allOption.text = "All";
      modelSelect.appendChild(allOption);

      data.forEach(model => {
        var option = document.createElement('option');
        option.value = model.name;
        option.text = model.name;
        modelSelect.appendChild(option);
      });
    });
}
