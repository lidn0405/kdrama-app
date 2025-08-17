function Home() {


    return (
        <div>
            <a href="/api/oauth2/authorization/google">
                Sign In With Google
            </a>
            <button>GET USERS</button>
            <button>GET DRAMAS</button>
            <button>GET REVIEWS</button>
            <button>GET COMMENTS</button>
        </div>
    )
}

export {
    Home
}