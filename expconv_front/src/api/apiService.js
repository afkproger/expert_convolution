async function refreshToken(apiBaseUrl) {
    try {
        const response = await fetch(`${apiBaseUrl}auth/refresh`,{
            method: "POST",
            credentials: "include"
        });
        if (!response.ok) {
            throw new Error("Failed to refresh token");
        }
        console.log("Refresh token successfully");
        return true;
    }catch(err){
        console.error("Refresh token failed" , err);
        return false;

    }
}

export async function fetchWithAuth(url , options = {} , apiBaseUrl  , router){
    let response = await fetch(url, {
        ...options,
        credentials: "include"
    });

    if (response.status === 401) {
        console.log("Trying to refresh token");

        const refreshed = await refreshToken(apiBaseUrl);
        if (refreshed) {
            response = await fetch(url, {
                ...options,
                credentials: "include"
            });
        }else {
            console.error("Failed to refresh token");
            router.push('/login');
        }
    }

    return response;
}