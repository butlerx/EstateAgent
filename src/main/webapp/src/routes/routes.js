import DashboardLayout from '../components/Dashboard/Layout/DashboardLayout';
import NotFound from '../components/GeneralViews/NotFoundPage';
import Overview from '../components/Dashboard/Views/Overview';
import PropertiesList from '../components/Dashboard/Views/Properties';

export default [
  {
    path: '/',
    component: DashboardLayout,
    redirect: '/admin',
  },
  {
    path: '/admin',
    component: DashboardLayout,
    children: [
      {
        path: 'overview',
        name: 'overview',
        component: Overview,
      },
      {
        path: 'properties',
        name: 'properties',
        component: PropertiesList,
      },
    ],
  },
  { path: '*', component: NotFound },
];
