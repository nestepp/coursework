import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import authHeader from "../services/auth-header";

export default function Admin() {
  const [users, setUsers] = useState([]);

  const { id } = useParams();

  const API_URL = 'https://backend-w453.onrender.com/api/users';

  useEffect(() => {
    loadUsers();
  }, []);

  const loadUsers = async () => {
    
        const result = await axios.get(API_URL, { headers: authHeader() });
        setUsers(result.data);
    
  };

  const deleteUser = async (id) => {
    await axios.delete(`https://backend-w453.onrender.com/api/users/${id}`, { headers: authHeader() });
    loadUsers();
  };

  return (
    <div className="container">
      <div className="py-4">
      <Link className="btn btn-outline-dark" to="/register">
            Добавить пользователя
          </Link>
        <hr/>

        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">Имя пользователя</th>
              <th scope="col">Почта</th>
            </tr>
          </thead>
          <tbody>
            {users.map((user) => (
              <tr>
                <td>{user.username}</td>
                <td>{user.email}</td>
                <td>
                  <button
                    className="btn btn-danger mx-2"
                    onClick={() => deleteUser(user.id)}
                  >
                    Удалить
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
