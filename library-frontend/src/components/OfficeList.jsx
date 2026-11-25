import React,{useEffect,useState} from "react";
import axios from "axios";

function OfficeList(){
    const [offices,setOffices]=useState([])
    const [search, setSearch]=useState("")
    async function loadOffices(){
        try{
            const response=await axios.get("http://localhost:8080/api/Offices/All")
            setOffices(response.data)
        }
        catch(error){
            console.error(error)
            alert("Failed to load offices")
        }
    }
    useEffect(()=>{
        loadOffices()
    },[])
    async function deleteOffice(Id){
        if(!window.confirm("Are you sure you want to delete this office?"))return
    try{
        await axios.delete("http://localhost:8080/api/Offices/Delete/" +Id)
        alert("Office deleted successfully")
        loadOffices()
    }
    catch(error){
        console.error(error)
        if(error.response && error.response.status===409){
            alert("Office is used by a doctor")
        } else if(error.response && error.response.status===404){
            alert("Office not found")
        } 
        else {
            alert("Failed to delete office")
        }
    }
    }
    const filteredOffices=offices.filter((office)=>{
const term =search.toLowerCase()
return (
    (office.name || "").toLowerCase().includes(term) ||
    (office.roomNumber || "").toLowerCase().includes(term)
)
    }
    )
    return(
        <div>
            <h1>AVAILABLE OFFICES</h1>
        <label>Search:</label>
        <input type="text"
        placeholder="Office Name or Number"
            value={search}
                onChange={(e)=>setSearch(e.target.value)}
            />
            <table border="1" cellPadding="8" style={{ marginTop: "15px", borderClollapse: "collapse"}}>
                <thead>
                    <tr>
                        <th>Office Name</th>
                        <th>Office Number</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredOffices.length === 0 ? (
                        <tr>
                            <td colSpan="3">No offices found.</td>
                        </tr>
                    ) : (
filteredOffices.map((office)=> (
                        <tr key={office.id}>
                
                            <td>{office.name}</td>
                            <td>{office.roomNumber}</td>
                            <td>
                                <button onClick={()=>deleteOffice(office.id)}>Delete</button>
                                </td>
                            </tr>
                        ))
                    )}
                </tbody>
            </table>
        </div>
    )
}
export default OfficeList;