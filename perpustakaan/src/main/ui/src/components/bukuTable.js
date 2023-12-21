import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from "axios";
import editIcon from "./../asset/edit.png";
import deleteIcon from "./../asset/delete.JPG";
import "../App.css";


const BookDataTable = () => {

  const navigate = useNavigate();
  const baseURL = "http://localhost:8080";
  const [books, setbooks] = useState([]);

  const setbookData = () => {
    axios.get(baseURL + "/buku").then((response) => {
      setbooks(response.data);
    }).catch(error => {
      alert("Error Ocurred while loading data:" + error);
    });
  }

  useEffect(() => {
    setbookData();
  }, []);


  const removebook = (id) => {
    axios.delete(baseURL + "/buku/" + id).then((response) => {
      alert("book record " + id + " deleted!");
      setbookData();
      navigate('/read')

    }).catch(error => {
      alert("Error Ocurred in removebook:" + error);
    });
  }

  const removeAllbook = (id) => {
    axios.delete(baseURL + "/buku").then((response) => {
      alert("All books deleted!");
      setbookData();
      navigate('/read')
    }).catch(error => {
      alert("Error Ocurred in removebook:" + error);
    });
  }

  return (
    <div class="card-body">
      <br>
      </br>
      <nav>
        <button
          className="btn btn-primary nav-item active"
          onClick={() => navigate("/create")}>
          Create New book
        </button>
      </nav>


      <br></br>
      <div className="col-md-6">
        <h4>books List</h4>

        <div class="container">
          <div class="row">
            <div class="col-12">
              <table class="table table-bordered table-striped">
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Judul</th>
                    <th>Penulis</th>
                    <th>Penerbit</th>
                    <th>Tahun Terbit</th>

                    <th scope="col">Action</th>

                  </tr>
                </thead>
                <tbody>

                  {
                    books &&
                    books.map((book, index) => (

                      <tr>
                        <th scope="row">{book.id}</th>
                        <td>{book.judul}</td>
                        <td>{book.penulis}</td>
                        <td>{book.penerbit}</td>
                        <td>{book.tahun_terbit}</td>
                        <td >

                          <Link to={"/edit/" + book.id}><img src={editIcon} alt="Edit" width="50" height="30" title="Edit" />
                          </Link>


                          <button
                            onClick={() => removebook(book.id)} className="button"
                          > <img src={deleteIcon} alt="Remove" title="Remove" width="30" height="30" />
                          </button>

                        </td>
                      </tr>

                    ))
                  }

                </tbody>
              </table>
            </div>
          </div>
        </div>
        <button className="btn btn-sm btn-danger"
          onClick={() => removeAllbook()}>
          Remove All
        </button>
      </div>

    </div>

  );
}
export default BookDataTable;