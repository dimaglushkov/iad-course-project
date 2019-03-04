export class Review{
    id?: number;
    gameId: number;
    gameName: string;
    gameRate: number;
    description?: string;
}

export class ReviewList{
    personId?: number;
    gameName?: string;
    reviews: Review[];
}