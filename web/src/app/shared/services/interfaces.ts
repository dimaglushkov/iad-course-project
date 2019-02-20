export interface User{
    j_username: string
    j_password: string
}

export interface NewUser{
    nickname: string 
    email: string
    password: string
}

export interface UserInfo{
  getUserInfo(): any;
    success: boolean
    description: string
    nickname: string
    friends: Array<Friends> 
    games: Array<Games> 
    wishlist: Array<Wishlist> 
    reviews: Array<Reviews>  
}

export interface Games{
    gameid: number
    gamename: string
}

export interface Friends{
    friendname: string
    friendurl?: string
}
export interface Reviews{
    gameid: number
    gamename: string
    gamerate: number
}
export interface Wishlist{
    gameid: number
    gamename: string
}


