import { User } from "./user.interface";

export interface Message {
  id: string;
  user: User;
  message: string;
  reclamation: number;
  createdAt: string;
}