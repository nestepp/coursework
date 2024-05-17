import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import authHeader from "../services/auth-header";

export default function AddBook() {
  let navigate = useNavigate();

  const [book, setBook] = useState({
    title: "",
    authorName: "",
    publisher: "",
    publishYear: "",
    count: "",
    price: "",
  });

  const { title, authorName, publisher, publishYear, count, price } = book;

  const onInputChange = (e) => {
    setBook({ ...book, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("https://backend-w453.onrender.com/api/books", book, { headers: authHeader() });
    navigate("/book");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Добавление Книги</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="Title" className="form-label">
                Название
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter title"
                name="title"
                value={title}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Authorname" className="form-label">
                Имя Автора
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter Author Name"
                name="authorName"
                value={authorName}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Publisher" className="form-label">
                Издатель
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter publisher"
                name="publisher"
                value={publisher}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="PublishYear" className="form-label">
                Год издания
              </label>
              <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter publish year"
                  name="publishYear"
                  value={publishYear}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Count" className="form-label">
                Количество
              </label>
              <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter count"
                  name="count"
                  value={count}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Price" className="form-label">
                Цена
              </label>
              <input
                  type={"number"}
                  className="form-control"
                  placeholder="Enter price"
                  name="price"
                  value={price}
                  onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Сохранить
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/book">
              Отменить
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
