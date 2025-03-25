import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import { ListEmployees } from "../pages/ListEmployees"
import { Employees } from "../pages/CreatedEmployees"


export const RouterPage = () => {

    return (
        <>
            <Router>
                <Routes>
                    <Route exact path='/' element={ <ListEmployees />} />
                    <Route exact path='/crear' element={ <Employees />} />
                    {/* <Route exact path='/editar' element={} /> */}
                </Routes>
            </Router>
        </>
    )
}