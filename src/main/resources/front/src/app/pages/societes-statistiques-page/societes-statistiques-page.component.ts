import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { AjoutSocieteStatistiqueModalComponent } from './ajout-societe-statistique-modal/ajout-societe-statistique-modal.component';
import SocieteStatistique from '@models/societeStatistique.model';
import {SocieteStatistiquesService} from '@services/societeStatistique.service';
import { ActivatedRoute } from '@angular/router';
import { forkJoin } from 'rxjs';
import { SocieteService } from '@services/societe.service';
import { HttpResponse } from '@angular/common/http';
import Societe from '@models/societe.model';

@Component({
  selector: 'app-societes-statistiques-page',
  templateUrl: './societes-statistiques-page.component.html',
  styleUrls: ['./societes-statistiques-page.component.scss']
})
export class SocietesStatistiquesPageComponent implements OnInit {

  public paginationForm: FormGroup = this.formBuilder.group(
    {
      pageSize : new FormControl(6)
    }
  );

  public societesStatistiquesArrayPage: Array<SocieteStatistique> = [];

  public societesStatistiquesPage: number = 1;

  public societesStatistiquesCollectionSize: number = 0;

  public pageSize: number;

  public societe: Societe;

  private societesStatistiquesArray: Array<SocieteStatistique> = [];

  private idSociete: number;

  constructor(
    protected formBuilder: FormBuilder,
    protected modalService: NgbModal,
    protected toastService: ToastrService,
    protected societeService: SocieteService,
    protected societeStatistiqueService: SocieteStatistiquesService,
    protected activatedRoute : ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      (params) => {
        if( params ['idSociete'] )
        {
          this.idSociete = params ['idSociete'];
          forkJoin([
            this.societeService.findById(this.idSociete),
            this.societeStatistiqueService.findByIdSociete(this.idSociete)
          ]).subscribe(
            (results: any) => {
              this._onSocieteSuccess(results[0]);
              this._onSocieteStatistiquesSuccess(results[1]);
            }
          );
        }
      }
    );
  }

  public refreshSocietesStatistiques(): void {
    this.pageSize = this.pageSizeFormControl.value;
    this.societesStatistiquesArrayPage = this.societesStatistiquesArray
      .slice((this.societesStatistiquesPage - 1) * this.pageSize, (this.societesStatistiquesPage - 1) * this.pageSize + this.pageSize);
    this.societesStatistiquesCollectionSize = this.societesStatistiquesArray.length;
  }

  public openModal(): void {
    const modalRef = this.modalService.open(AjoutSocieteStatistiqueModalComponent, {size: 'md'});
    modalRef.componentInstance.idSociete = this.idSociete;
    modalRef.result.then(
      (result: SocieteStatistique) => {
        if (result) {
          this.addSocieteStatistique(result);
        }
      }
    );
  }

  public get pageSizeFormControl(): FormControl {
    return this.paginationForm.get('pageSize') as FormControl;
  }

  private addSocieteStatistique(societeStatistique: SocieteStatistique): void {
    const index: number = this.societesStatistiquesArray.findIndex( societeStatistiqueTemp => {
      return societeStatistiqueTemp.id === societeStatistique.id;
    });
    if(index === -1){
      this.societesStatistiquesArray.push(societeStatistique);
    } else {
      this.societesStatistiquesArray[index].prixGazoil = societeStatistique.prixGazoil;
      this.societesStatistiquesArray[index].prixEssence = societeStatistique.prixEssence;
    }
    this.refreshSocietesStatistiques();
  }

  private _onSocieteSuccess(response: HttpResponse<Societe>): void {
    this.societe = response.body;
  }

  private _onSocieteStatistiquesSuccess(response: HttpResponse<SocieteStatistique[]>): void {
    this.societesStatistiquesArray = response.body || [];
    this.refreshSocietesStatistiques();
  }
}
