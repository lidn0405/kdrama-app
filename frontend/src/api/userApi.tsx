const url = "/api/user"

async function getUsers(jwtToken: String) {
    try {
        const res = await fetch(`${url}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`,
                'Content-Type': `application/json`
            }
        });
        if (!res.ok) {
            throw new Error(`Response status: ${res.status}`);
        }
        const json = res.json();
        return json;
    } catch (error) {
        if (error instanceof Error) {
            console.log(error.message);
        } else {
            console.log(String(error));
        }
    }
}

export {
    getUsers,

}