import { CatsProposal } from './CatsProposal';

export interface ICatsProposalUI {
    // tslint:disable-next-line:typedef-whitespace
    // tslint:disable-next-line:callable-types
    selectCat: (catsProposal: CatsProposal, selectedCatId: string) => void ;
}
