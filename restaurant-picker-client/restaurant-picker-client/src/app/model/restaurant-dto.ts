import { Dish } from "./dish";

export class RestaurantDto {
    public name : string;
    public cuisine : string;
    public delivery : string;
    public menu : Dish[];

    constructor(){}
}
