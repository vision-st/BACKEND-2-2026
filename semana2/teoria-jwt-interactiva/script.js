const cards = document.querySelectorAll('[data-toggle-card]');
cards.forEach((card) => {
  const button = card.querySelector('[data-toggle-button]');
  const content = card.querySelector('[data-toggle-content]');
  button.addEventListener('click', () => {
    content.classList.toggle('open');
    button.textContent = content.classList.contains('open') ? 'Ocultar ejemplo' : 'Ver ejemplo';
  });
});

const modes = {
  stateful: {
    title: 'Autenticación con estado (Stateful)',
    items: [
      'El servidor guarda la sesión del usuario.',
      'Cada solicitud depende de una sesión previa.',
      'Es cómoda para aplicaciones tradicionales con formularios.',
      'Escala peor en arquitecturas distribuidas.'
    ]
  },
  stateless: {
    title: 'Autenticación sin estado (Stateless)',
    items: [
      'El servidor no guarda sesiones del usuario.',
      'Cada solicitud lleva su propia evidencia de autenticación, por ejemplo un JWT.',
      'Se adapta mejor a APIs REST y microservicios.',
      'Exige validar token, expiración, firma y transporte seguro.'
    ]
  }
};

const modeButtons = document.querySelectorAll('.switcher__btn');
const modeBox = document.getElementById('mode-box');
modeButtons.forEach((button) => {
  button.addEventListener('click', () => {
    modeButtons.forEach((btn) => btn.classList.remove('active'));
    button.classList.add('active');
    const mode = modes[button.dataset.mode];
    modeBox.innerHTML = `<h3>${mode.title}</h3><ul>${mode.items.map((item) => `<li>${item}</li>`).join('')}</ul>`;
  });
});

const splitButton = document.getElementById('split-token-btn');
const tokenOutput = document.getElementById('token-parts');
splitButton.addEventListener('click', () => {
  const token = document.getElementById('jwt-sample').textContent.trim();
  const parts = token.split('.');
  const labels = ['Header', 'Payload', 'Signature'];
  tokenOutput.innerHTML = parts.map((part, index) => `
    <div class="token-part">
      <strong>${labels[index] || 'Parte extra'}:</strong>
      <div>${part}</div>
    </div>
  `).join('');
});

const quizButtons = document.querySelectorAll('[data-answer]');
const quizResult = document.getElementById('quiz-result');
quizButtons.forEach((button) => {
  button.addEventListener('click', () => {
    if (button.dataset.answer === 'ok') {
      quizResult.textContent = 'Correcto: el payload contiene claims como sub, exp, roles o permisos.';
      quizResult.style.color = '#8ef0ad';
    } else {
      quizResult.textContent = 'Casi: header define metadatos y signature valida integridad. Las claims van en el payload.';
      quizResult.style.color = '#ff9c9c';
    }
  });
});
