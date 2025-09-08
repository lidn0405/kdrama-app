import { Link } from "react-router"
import { getUsers } from "../../api/UserApi";

function Home() {

    async function handleLogout() {
        try {
            // 1. Call the backend logout endpoint
            const response = await fetch('http://localhost:5173/api/logout', {
            method: 'POST',
            headers: {
                // If you are using Bearer token authentication for other requests,
                // you might need to include the Authorization header here as well.
                'Authorization': `Bearer ${localStorage.getItem('token')}` 
            }
            });

            if (response.ok) {
            // 2. Clear the token from local storage
            localStorage.removeItem('token');

            // 3. Redirect the user to the login page or home page
            window.location.href = '/login'; 
            console.log('Successfully logged out');
            } else {
            console.error('Logout failed');
            }
        } catch (error) {
            console.error('An error occurred during logout:', error);
            }
        }

    async function users() {
        const users = await getUsers();
        console.log(users);
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