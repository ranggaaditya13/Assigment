import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';

function App() {
    const [tasks, setTasks] = useState([]);
    const [judul, setJudul] = useState('');
    const [penulis, setPenulis] = useState('');
    const [penerbit, setPenerbit] = useState('');
    const [tahun_terbit, setTahun_terbit] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/buku')
            .then(response => {
                setTasks(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);

    const handleAddTask = () => {
        axios.post('http://localhost:8080/buku',{
                judul: judul,
                penulis: penulis,
                penerbit:penerbit,
                tahun_terbit:tahun_terbit

        })
            .then(response => {
                setTasks([...tasks, response.data]);
                setJudul('');
                setPenulis('');
                setPenerbit('');
                setTahun_terbit('');
            })
            .catch(error => {
                console.error('Error adding task:', error);
            });
    };

    return (
        <div className="App">
            <h1>Task List</h1>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">id</th>
                  <th scope="col">Judul</th>
                  <th scope="col">Penulis</th>
                  <th scope="col">Penerbit</th>
                  <th scope="col">Tahun Terbit</th>
                </tr>
              </thead>
              <tbody>
              {tasks.map(task => (
                <tr>
                    <td key={task.id}>{task.id}</td>
                    <td key={task.id}>{task.judul}</td>
                    <td key={task.id}>{task.penerbit}</td>
                    <td key={task.id}>{task.judul}</td>
                    <td key={task.id}>{task.tahun_terbit}</td>
                    
                </tr>
                ))}
              </tbody>
            </table>
            <div>
              
                <input
                    type="text"
                    placeholder="Judul"
                    value={judul}
                    name='judul'
                    onChange={(e) => setJudul(e.target.value)}
                />
                <input
                    type="text"
                    name='penulis'
                    placeholder="Penulis"
                    value={penulis}
                    onChange={(e) => setPenulis(e.target.value)}
                />
                <input
                    type="text"
                    name='penerbit'
                    placeholder="Penerbit"
                    value={penerbit}
                    onChange={(e) => setPenerbit(e.target.value)}
                />
                <input
                    type="text"
                    name='tahun_terbit'
                    placeholder="Tahun Terbit"
                    value={tahun_terbit}
                    onChange={(e) => setTahun_terbit(e.target.value)}
                />
            
                <button onClick={()=> handleAddTask}>Add Buku</button>
            </div>
        </div>
    );
}

export default App;