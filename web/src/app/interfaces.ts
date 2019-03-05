export interface ResponseState{
    success: boolean;
    description: string;
}

export interface Review{
    id: number;
    gameId: number;
    gameName: string;
    gameRate: number;
    description?: string;
    author?: string;
}

export interface ReviewList{
    state: ResponseState;
    reviews?: Review[];
    author?: string;
}

export interface Message{
    id: number;
    from: string;
    to: string;
    topic: string;
    text: string;
    date: Date;
}

export interface MessageList{
    state: ResponseState;
    messages: Message[];
}






































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

export interface Game{
    gameid: number
    gamename: string
    gamerate: number
} 
