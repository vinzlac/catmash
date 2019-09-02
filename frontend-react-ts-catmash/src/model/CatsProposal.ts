export interface CatsProposal {
  uuid: string;
  cats: Cat[];
}

export interface Cat {
  id: string;
  url: string;
}
