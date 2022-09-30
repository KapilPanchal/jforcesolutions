import { Role } from "./enum/role";

export interface Appuser {
    id: number;
    username: string;
    password: string;
    emailId: string;
    phoneNo: number;
    role: Role;
}
