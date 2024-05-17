import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://backend-w453.onrender.com/api/books/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'info');
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
