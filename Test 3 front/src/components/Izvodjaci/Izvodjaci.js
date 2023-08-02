import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate } from 'react-router-dom';
import IzvodjacRow from './IzvodjacRow';

const Izvodjaci = () => {

    //navigate
    const navigate = useNavigate()

    const init = {
        ime: '',
        zanr: '',
        drzava: '',
        brojClanova: ''
    }

    //state
    const [izvodjaci, setizvodjaci] = useState([])
    const[noviIzvodjac, setNoviIzvodjac] = useState(init)
    const [validno, setValidno] = useState(false)
    
    //dobavljanje svih
    const getIzvodjaci = useCallback(() => {

        Axios.get('/izvodjaci')
            .then(res => {
                console.log(res);
                setizvodjaci(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);


    useEffect(() => {
        getIzvodjaci()
    }, [])

    const dodaj = () => {

        const dto = {
            ime: noviIzvodjac.ime,
            zanr: noviIzvodjac.zanr,
            drzava: noviIzvodjac.drzava,
            brojClanova: noviIzvodjac.brojClanova
        }

        Axios.post('/izvodjaci', dto)
            .then(res => {
                console.log(res)
                alert('Uspesno dodavanje!')
                window.location.reload()
            })
            .catch(error => {
                console.log(error)
                alert('Doslo je do greske, pokusajte ponovo!')
            })
    }


    //ispis redova u tabeli
    const renderIzvodjaci = () => {
        return izvodjaci.map((izvodjac, index) => {
            return <IzvodjacRow key={izvodjac.id} izvodjac={izvodjac}></IzvodjacRow>
        })
    }

      //validacija
      const validiraj = () => {
        if (noviIzvodjac.ime == '' || noviIzvodjac.zanr == '' || noviIzvodjac.drzava  == ''|| noviIzvodjac.brojClanova == '') {
            setValidno(false)
        } else {
            setValidno(true)
        }
    }

 //onChange
 const inputValueChange = (e) => {
    let input = e.target
    let name = input.name
    let value = input.value
    let noviCopy = noviIzvodjac   
    noviCopy[name] = value
    setNoviIzvodjac(noviCopy)
    validiraj()
}


//forma za dodavanje
    const renderDodavanje = ()=>{
        return(
            <>
            <Row>
            <Col></Col>
            <Col xs="12" sm="10" md="8">
                <Form>
                    <Form.Label htmlFor="ime" >Ime</Form.Label>
                        <Form.Control  name="ime" id="ime" type="text" onChange={(e) => inputValueChange(e)} />
                    <Form.Label htmlFor="drzava">Drzava porekla</Form.Label>
                        <Form.Control  name="drzava" id="drzava" type="text" onChange={(e) => inputValueChange(e)} />
                    <Form.Label htmlFor="zanr">Zanr</Form.Label>
                        <Form.Control  name="zanr" id="zanr" type="text" onChange={(e) => inputValueChange(e)} />
                    <Form.Label htmlFor="brojClanova">Broj clanova</Form.Label>
                        <Form.Control  name="brojClanova" id="brojClanova" type="number" onChange={(e) => inputValueChange(e)} />
                    
                    <br/><Button onClick={dodaj}  disabled={!validno}> Kreiraj</Button>
                </Form>
            </Col>
            <Col></Col>
        </Row>
               
            </>
        )
    }


    //krajnji ispis
    return (
        <Col>
            <Row><h1>Izvodjaci</h1></Row>
            <Row>
            {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? renderDodavanje():null}
            </Row>

            <br/><br/>
            <Row><Col>
                   

                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Naziv</th>
                            <th>Zanr</th>
                            <th>Drzava porekla</th>
                            <th>Broj clanova</th>
                        </tr>
                    </thead>
                    <tbody>
                        {renderIzvodjaci()}
                    </tbody>
                </Table>
            </Col>
            </Row>
        </Col>
    )
}

export default Izvodjaci

