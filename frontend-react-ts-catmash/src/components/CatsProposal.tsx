import * as React from 'react';
import { ICatsProposalUI } from '../model/ICatsProposalUI';
import './CatsProposal.css';

interface CatsProposal {
  uuid: string;
  cats: Cat[];
}

interface Cat {
  id: string;
  url: string;
}

interface CatsProposalProps {
  catsProposal: CatsProposal;
  catsProposalUI: ICatsProposalUI;
}

class CatsProposalComponent extends React.Component<CatsProposalProps, any> {

  public render() {
    return (
      <div>
        <h2>Click on your prefered cat</h2>
        <div key={this.props.catsProposal.uuid} className="cats-proposal__choice">
          {this.props.catsProposal.cats.map((cat: Cat) =>
            <div key={cat.id}>
              <img src={cat.url} alt={cat.url} width="400"
              onClick={() => this.props.catsProposalUI.selectCat(this.props.catsProposal, cat.id)}/>
            </div>
          )}
        </div>
      </div>
    );
  }

}

export default CatsProposalComponent;
