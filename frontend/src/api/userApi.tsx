const url = "/api/user"

async function getUsers(jwtToken: string) {
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
        const json = await res.json();
        return json;
    } catch (error) {
        throw error;
    }
}

export {
    getUsers,

}