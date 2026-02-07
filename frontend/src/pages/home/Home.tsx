import { Link } from "react-router"
import { getUsers } from "../../api/userApi";
import { useEffect } from "react";

function Home() {

    useEffect(() => {
        
    }, [])

    async function handleLogout() {
        if (localStorage.getItem('authToken') != null) {
            localStorage.removeItem('authToken');
            console.log("AUTH TOKEN REMOVED");
        } else {
            console.log("NO AUTH TOKEN FOUND");
        }
    }

    async function users() {
        const token = localStorage.getItem('authToken'); // Get token from storage
        if (token) {
            const users = await getUsers(token);
            console.log("Fetched users:", users);
        } else {
            console.error("No token found. Please log in.");
        }
    }

    return (
        <div>
            <div>
                <Link to={"/login"}>Login</Link>
            </div>
            <div>
                <button onClick={handleLogout}>Logout</button>
            </div>
            <div>
                <button onClick={users}>GET USERS</button>
            </div>
            <button>GET DRAMAS</button>
            <button>GET REVIEWS</button>
            <button>GET COMMENTS</button>
        </div>
    )
}

export {
    Home
}