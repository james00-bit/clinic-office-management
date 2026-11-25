import React, { useState } from "react"
import axios from "axios"

function Office() {
  const [name, setName] = useState("")
  const [officeNumber, setOfficeNumber] = useState("")
  const [deleteName, setDeleteName] = useState("")
  const [deleteNumber, setDeleteNumber] = useState("")

  async function saveOffice() {
    try {
      await axios.post("http://localhost:8080/api/Offices/Save", {
        name: name,
        roomNumber: officeNumber,
      })
      alert("Office saved successfully")
      setName("")
      setOfficeNumber("")
    } catch (error) {
      console.error(error)
      alert("Failed to save office")
    }
  }

  async function deleteOffice() {
    try {
      await axios.delete("http://localhost:8080/api/Offices/Delete", {
        params: { name: deleteName, roomNumber: deleteNumber },
      })
      alert("Office deleted successfully")
      setDeleteName("")
      setDeleteNumber("")
    } catch (error) {
      console.error(error)
      if (error.response && error.response.status === 404) {
        alert("Office not found")
      } else if (error.response && error.response.status === 409) {
        alert("Office is used by a doctor")
      } else {
        alert("Failed to delete office")
      }
    }
  }

  return (
    <div>
      <h1>SAVE OFFICE</h1>
      <label>Office Name:</label>
      <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
      <br />
      <label>Office Number:</label>
      <input type="text" value={officeNumber} onChange={(e) => setOfficeNumber(e.target.value)} />
      <br />
      <button onClick={saveOffice}>Save</button>

      <h1>DELETE OFFICE</h1>
      <label>Office Name:</label>
      <input type="text" value={deleteName} onChange={(e) => setDeleteName(e.target.value)} />
      <br />
      <label>Office Number:</label>
      <input type="text" value={deleteNumber} onChange={(e) => setDeleteNumber(e.target.value)} />
      <br />
      <button onClick={deleteOffice}>Delete</button>
    </div>
  )
}

export default Office