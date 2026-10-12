const focusDialog = document.querySelector('#focusDialog');
const focusClock = document.querySelector('#focusClock');
const focusStatus = document.querySelector('#focusStatus');
const focusStart = document.querySelector('#focusStart');
const focusSessions = document.querySelector('#focusSessions');
const focusModes = { focus: 25 * 60, short: 5 * 60, long: 15 * 60 };
let focusMode = 'focus';
let remainingSeconds = focusModes[focusMode];
let focusInterval = null;
let completedSessions = Number(localStorage.getItem('daylist-focus-sessions') || 0);

function renderFocusClock() {
  const minutes = Math.floor(remainingSeconds / 60);
  const seconds = remainingSeconds % 60;
  focusClock.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

function stopFocusTimer(message = 'Timer paused.') {
  clearInterval(focusInterval);
  focusInterval = null;
  focusStart.textContent = 'Resume';
  focusStatus.textContent = message;
}

document.querySelector('#focusLaunch').addEventListener('click', () => focusDialog.showModal());
document.querySelector('#focusClose').addEventListener('click', () => focusDialog.close());
focusDialog.addEventListener('close', () => {
  if (focusInterval) stopFocusTimer('Paused while you were away.');
});

document.querySelectorAll('[data-focus-mode]').forEach((button) => {
  button.addEventListener('click', () => {
    if (focusInterval) stopFocusTimer('Mode changed.');
    focusMode = button.dataset.focusMode;
    remainingSeconds = focusModes[focusMode];
    document.querySelectorAll('[data-focus-mode]').forEach((modeButton) => {
      const selected = modeButton === button;
      modeButton.classList.toggle('is-selected', selected);
      modeButton.setAttribute('aria-pressed', String(selected));
    });
    focusStart.textContent = focusMode === 'focus' ? 'Start focus' : 'Start break';
    focusStatus.textContent = 'Ready when you are.';
    renderFocusClock();
  });
});

focusStart.addEventListener('click', () => {
  if (focusInterval) {
    stopFocusTimer();
    return;
  }
  focusStart.textContent = 'Pause';
  focusStatus.textContent = focusMode === 'focus' ? 'One thing at a time.' : 'Take a proper breather.';
  focusInterval = setInterval(() => {
    remainingSeconds -= 1;
    renderFocusClock();
    if (remainingSeconds <= 0) {
      stopFocusTimer(focusMode === 'focus' ? 'Session complete. Nice work.' : 'Break complete. Ready to focus?');
      focusStart.textContent = 'Start again';
      if (focusMode === 'focus') {
        completedSessions += 1;
        localStorage.setItem('daylist-focus-sessions', String(completedSessions));
        focusSessions.textContent = completedSessions;
      }
    }
  }, 1000);
});

document.querySelector('#focusReset').addEventListener('click', () => {
  clearInterval(focusInterval);
  focusInterval = null;
  remainingSeconds = focusModes[focusMode];
  focusStart.textContent = focusMode === 'focus' ? 'Start focus' : 'Start break';
  focusStatus.textContent = 'Timer reset.';
  renderFocusClock();
});

focusSessions.textContent = completedSessions;
renderFocusClock();