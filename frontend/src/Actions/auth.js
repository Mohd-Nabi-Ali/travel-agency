import axios from 'axios';
import {useNavigate} from "react-router-dom";
export async function login(body) {
    try {
        const response = await axios.post("http://localhost:8080/api/auth/login", {
            email: body.username,
            password: body.password
        }).then(response => {
            if (response.data) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }
        })
    } catch (err) {
        console.error(err.message);
    }
}