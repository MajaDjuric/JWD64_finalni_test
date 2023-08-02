import { useNavigate } from "react-router-dom";
import Axios from "../../apis/Axios";
import { Button } from "react-bootstrap";
import React, { useCallback, useEffect, useState } from "react";


const NastupiRow = (props) => {

    const nastupId = props.nastup.id;  
    
    ///navigate
    var navigate = useNavigate()

    
    //brisanje 
    const deleteNastup = () => {
        Axios.delete('/nastupi/' + nastupId)
            .then(res => {
                console.log(res);
                alert('Uspesno brisanje!');
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske, pokusajte ponovo!');
            });
    }
    

//krajnji ispis

    return (
        <tr  key={props.nastup.id}>
            <td>{props.nastup.izvodjacIme}</td>
            <td>{props.nastup.festivalNaziv}</td>
            {window.localStorage.getItem('role') == 'ROLE_ADMIN' && window.localStorage["jwt"] ? <td><Button variant="danger" onClick={deleteNastup}>Izbrisi</Button></td>:null}
        </tr>
    )
}

export default NastupiRow;