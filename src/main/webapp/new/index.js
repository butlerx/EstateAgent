function processForm(e) {
  if (e.preventDefault) e.preventDefault();
  axios
    .post('/api/property', {
      type: document.getElementsByName('type')[0].value,
      district: document.getElementsByName('district')[0].value,
      bedrooms: document.getElementsByName('bedrooms')[0].value,
      daysLeft: document.getElementsByName('daysLeft')[0].value,
      price: document.getElementsByName('price')[0].value,
    })
    .then(({ headers }) => {
      window.location = `${window.location.origin}/property/#${
        new URL(headers.location).pathname.split('/')[3]
      }`;
    })
    .catch(err => console.error(err));

  return false;
}

const form = document.getElementById('create');
if (form.attachEvent) {
  form.attachEvent('submit', processForm);
} else {
  form.addEventListener('submit', processForm);
}
