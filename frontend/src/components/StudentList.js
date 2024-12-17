import React, { useState, useEffect } from "react";
import axios from "axios";

function StudentList() {
  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    email: "",
  });

  useEffect(() => {
    fetchStudents();
  }, []);

  const fetchStudents = async () => {
    try {
      const response = await axios.get("http://localhost:8080/students");
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    }
  };

  const addStudent = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/students", newStudent);
      fetchStudents();
      setNewStudent({ id: "", name: "", email: "" }); // Reset form
    } catch (error) {
      console.error("Error adding student:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewStudent({ ...newStudent, [name]: value });
  };

  return (
    <div>
      <h2>Student List</h2>
      <ul>
        {students.map((student) => (
          <li key={student.id}>
            {student.id}: {student.name} ({student.email})
          </li>
        ))}
      </ul>

      <h3>Add New Student</h3>
      <form onSubmit={addStudent}>
        <label>
          ID:
          <input type="number" name="id" value={newStudent.id} onChange={handleChange} />
        </label>
        <br />
        <label>
          Name:
          <input type="text" name="name" value={newStudent.name} onChange={handleChange} />
        </label>
        <br />
        <label>
          Email:
          <input type="email" name="email" value={newStudent.email} onChange={handleChange} />
        </label>
        <br />
        <button type="submit">Add Student</button>
      </form>
    </div>
  );
}

export default StudentList;
