/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { EntityPageDetailsMySuffixDetailComponent } from 'app/entities/entity-page-details-my-suffix/entity-page-details-my-suffix-detail.component';
import { EntityPageDetailsMySuffix } from 'app/shared/model/entity-page-details-my-suffix.model';

describe('Component Tests', () => {
    describe('EntityPageDetailsMySuffix Management Detail Component', () => {
        let comp: EntityPageDetailsMySuffixDetailComponent;
        let fixture: ComponentFixture<EntityPageDetailsMySuffixDetailComponent>;
        const route = ({ data: of({ entityPageDetails: new EntityPageDetailsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [EntityPageDetailsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EntityPageDetailsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EntityPageDetailsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.entityPageDetails).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
