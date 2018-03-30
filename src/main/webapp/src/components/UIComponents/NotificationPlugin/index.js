import Notifications from './Notifications';

const NotificationStore = {
  state: [], // here the notifications will be added

  removeNotification(index) {
    this.state.splice(index, 1);
  },
  notify(notification) {
    this.state.push(notification);
  },
};

export default {
  install(Vue) {
    Object.defineProperty(Vue.prototype, '$notifications', {
      get() {
        return NotificationStore;
      },
    });
    Vue.component('Notifications', Notifications);
  },
};
