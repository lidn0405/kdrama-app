import { Outlet } from "react-router"

function Layout() {
    return (
        <div>
            <Outlet />
        </div>
    )
}

export {
    Layout
}