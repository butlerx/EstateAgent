function output({ bedrooms, bid, district, end, id, price, start, type, freeViewings }) {
  document.getElementById('property').innerHTML = `<div>
    <p>id: ${id}</p>
    <p>type: ${type}</p>
    <p>bedrooms: ${bedrooms}</p>
    <p>district: ${district}</p>
    <p>start: ${start}</p>
    <p>end: ${end}</p>
    <p>price: ${price}</p>
    ${
      bid.offer === 0
        ? '<p>No current offers</p>'
        : `<p>current offer of ${bid.offer} from ${bid.bidder}</p>`
    }
    <input id="offer" type="number" placeholder="bid">
    <input type="text" id="user" placeholder="name">
    <button id="bid">Make offer</button>
    <span id="offer-status"></span>
    </div>`;
  document.getElementById('schedule').innerHTML = `
    <input type="text" id="user-book" placeholder="name">
    <button id="book">book</button>
    <span id="book-status"></span>
    <div>${freeViewings
      .map(
        time => `<div>
          <input type="radio" name="booking" value="${time} id="radio">
          <label for="radio">${time}</label>
        </div>`,
      )
      .join('')}</div>`;
  document.getElementById('bid').addEventListener('click', makeOffer, false);
  document.getElementById('book').addEventListener('click', book, false);
  return Promise.resolve();
}

function update(id) {
  axios
    .get(`/api/property/${id}`, {})
    .then(({ data }) => output(data))
    .catch(() => {
      document.getElementById('property').innerHTML = '<div><p>No Property With that id</p></div>';
    });
}

function book() {
  const time = document.querySelector('input[name="booking"]:checked').value;
  const booker = document.getElementById('user-book').value;
  const id = window.location.hash.substring(1);
  axios
    .post(`/api/property/${id}/booking`, {
      booker,
      time,
    })
    .then(({ data }) => {
      document.getElementById('book-status').innerHTML = data;
    })
    .catch(err => {
      document.getElementById('book-status').innerHTML = 'failed to book select another time';
      console.error(err);
    });
}

function makeOffer() {
  const offer = document.getElementById('offer').value;
  const bidder = document.getElementById('user').value;
  const id = window.location.hash.substring(1);
  axios
    .post(`/api/property/${id}/bid`, {
      bidder,
      offer,
    })
    .then(() => {
      update(id);
    })
    .catch(() => {
      document.getElementById('offer-status').innerHTML = 'Sorry your Bid is too low';
    });
}

document.getElementById('submit').addEventListener(
  'click',
  () => {
    const search = document.getElementById('search').value;
    update(search);
    window.location.hash = `#${search}`;
  },
  false,
);

update(window.location.hash.substring(1));
