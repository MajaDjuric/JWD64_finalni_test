import Axios from '../../apis/Axios';
import { useCallback, useEffect, useState } from "react"
import { Button, Col, Form, Row, Table } from "react-bootstrap"
import { useNavigate } from 'react-router-dom';
import NastupiRow from './NastupiRow';

const Nastupi = () => {

    //navigate
    const navigate = useNavigate()

    const init = {
        izvodjacId: '',
        festivalId: ''
    }

    //state
    const [nastupi, setNastupi] = useState([])
    const [festivali, setFestivali] = useState([])
    const [izvodjaci, setizvodjaci] = useState([])
    const [noviNastup, setNoviNastup] = useState(init)
    const [pageNo, setPageNo] = useState(0)
    const [totalPage, setTotalPage] = useState(0)
    const [parametriPretrage, setParametriPretrage] = useState({
        festivalId: '',
        izvodjacId: '',
    })

    //dobavljanje svih
    const getNastupi = useCallback((nextPage) => {

        const config = {
            params: {
                pageNo: nextPage,
                festivalId: parametriPretrage.festivalId,
                izvodjacId: parametriPretrage.izvodjacId
            }
        }


        Axios.get('/nastupi', config)
            .then(res => {
                console.log(res);
                setNastupi(res.data)
                setPageNo(nextPage)
                setTotalPage(res.headers["total-pages"])
                console.log(res.headers)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

    //dobavljanje za pretragu
    const getFestivali = useCallback(() => {
        Axios.get('/festivali')
            .then(res => {
                console.log(res);
                setFestivali(res.data)
            })
            .catch(error => {
                console.log(error);
                alert('Doslo je do greske!')
            });
    }, []);

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
        getFestivali()
        getIzvodjaci()
        getNastupi(0)
    }, [])

    //ispis redova u tabeli
    const renderNastupi = () => {
        return nastupi.map((nastup, index) => {
            return <NastupiRow key={nastup.id} nastup={nastup}></NastupiRow>
        })
    }

    //ispis za select
    const festivaliSelect = () => {
        return festivali.map(festival => {
            return (
                <option key={festival.id} value={festival.id}>{festival.naziv}</option>
            )
        })
    }

    const izvodjacSelect = () => {
        return izvodjaci.map(izvodjac => {
            return (
                <option key={izvodjac.id} value={izvodjac.id}>{izvodjac.ime}</option>
            )
        })
    }

    //onchange
    const inputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let noviCopy = noviNastup
        noviCopy[name] = value
        setNoviNastup(noviCopy)
        console.log(noviNastup.festivalId)
        console.log(noviNastup.izvodjacId)

    }

    const pretragaInputValueChange = (e) => {
        let input = e.target
        let name = input.name
        let value = input.value
        let parametriPretrageCopy = parametriPretrage
        parametriPretrageCopy[name] = value
        setParametriPretrage(parametriPretrageCopy)
        getNastupi(0)
    }

    const dodaj = () => {

        const dto = {
            izvodjacId: noviNastup.izvodjacId,
            festivalId: noviNastup.festivalId
        }

        Axios.post('/nastupi', dto)
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

    //dodavanje
    const renderDodavanje = () => {
        return (
            <>
                <Form >
                    <Row>
                        <Col>
                            <Form.Label>Izvodjac</Form.Label>
                            <Form.Select name="izvodjacId" onChange={(e) => inputValueChange(e)}>
                                <option value={''}></option>
                                {izvodjacSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label>Festival</Form.Label>
                            <Form.Select name="festivalId" onChange={(e) => inputValueChange(e)}>
                                <option value={''}></option>
                                {festivaliSelect()}
                            </Form.Select>
                        </Col>

                    </Row>
                    <br /><Button onClick={dodaj} >Kreiraj</Button>
                </Form>

            </>
        )
    }

    //forma za pretragu
    const renderPretraga = () => {
        return (
            <>
                <Row><h3>Pretraga</h3></Row>
                <Form >

                    <Row>
                        <Col>
                            <Form.Label>Izvodjac</Form.Label>
                            <Form.Select name="izvodjacId" onChange={(e) => pretragaInputValueChange(e)}>
                                <option value={''}></option>
                                {izvodjacSelect()}
                            </Form.Select>
                        </Col>
                        <Col>
                            <Form.Label>Festival</Form.Label>
                            <Form.Select name="festivalId" onChange={(e) => pretragaInputValueChange(e)}>
                                <option value={''}></option>
                                {festivaliSelect()}
                            </Form.Select>

                        </Col>
                    </Row>
                </Form>

            </>
        )
    }


    //krajnji ispis
    return (
        <Col>
            <Row><h1>Nastupi</h1></Row>
            <Row>
                {window.localStorage["jwt"] ? renderDodavanje() : null}
            </Row>
            <br /><Row>
                {renderPretraga()}
            </Row>

            <br /><br />
            <Row><Col>


                <Table style={{ marginTop: 5 }}>
                    <thead>
                        <tr>
                            <th>Izvodjac</th>
                            <th>Festival</th>
                            {window.localStorage.getItem('role') == 'ROLE_ADMIN' ? <th></th> : null}

                        </tr>
                    </thead>
                    <tbody>
                        {renderNastupi()}
                    </tbody>
                </Table>
            </Col>
            </Row>
            <Row>
                <Col style={{ display: 'flex', justifyContent: 'flex-start' }}>
                    <Button disabled={pageNo == 0} onClick={() => getNastupi(pageNo - 1)}>Prethodna</Button>
                    <Button disabled={pageNo + 1 == totalPage || nastupi.length == 0} onClick={() => getNastupi(pageNo + 1)}>Sledeca</Button>
                </Col>
            </Row>
        </Col>
    )
}

export default Nastupi

