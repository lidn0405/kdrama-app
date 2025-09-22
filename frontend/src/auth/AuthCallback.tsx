import { useEffect, useRef } from 'react';
import { useNavigate } from 'react-router';

function AuthCallback() {
    const navigate = useNavigate();
    const effectRan = useRef(false);

    useEffect(() => {
        if (effectRan.current === true) {
            return;
        }

        const queryParams = new URLSearchParams(window.location.search);
        const token = queryParams.get('token');

        if (token) {
            console.log('Token received, saving to localStorage...');
            localStorage.setItem('authToken', token);
            window.history.replaceState({}, document.title, window.location.pathname);
            navigate('/');
        } else {
            console.error('Authentication failed: No token provided.');
            navigate('/login');
        }
        
        return () => {
            effectRan.current = true;
        };
    }, []);

    return (
        <div>
        <h1>Authenticating...</h1>
        <p>Please wait while we log you in.</p>
        </div>
    );
}

export {
    AuthCallback
}